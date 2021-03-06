package com.vrv.vap.exportAndImport.word.render;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.config.Configure;
import com.vrv.vap.exportAndImport.word.config.Name;
import com.vrv.vap.exportAndImport.word.data.TextRenderData;
import com.vrv.vap.exportAndImport.word.exception.RenderException;
import com.vrv.vap.exportAndImport.word.policy.DocxRenderPolicy;
import com.vrv.vap.exportAndImport.word.policy.RenderPolicy;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;

/**
 * * 
 * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  
 * @version 创建时间：2018年9月20日 上午9:38:10
 * 类说明      RenderAPI
 */
public class RenderAPI {

	private static final Logger logger = LoggerFactory.getLogger(RenderAPI.class);

	/**
	 * 协助调试：判断是否有缺失模板
	 * 
	 * @param template
	 * @param datas
	 */
	public static void debug(XWPFTemplate template, Map<String, Object> datas) {
		List<ElementTemplate> all = template.getElementTemplates();
		logger.debug("Template tag number is:{}", (null == all ? 0 : all.size()));
		if ((all == null || all.isEmpty()) && (null == datas || datas.isEmpty())) {
			logger.debug("No template gramer find and no render data find");
			return;
		}
		Set<String> tagtKeys = new HashSet<String>();
		for (ElementTemplate ele : all) {
			logger.debug("Parse the tag：{}", ele.getTagName());
			tagtKeys.add(ele.getTagName());
		}

		Set<String> keySet = datas.keySet();
		HashSet<String> copySet = new HashSet<String>(keySet);

		copySet.removeAll(tagtKeys);
		Iterator<String> iterator = copySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			logger.warn("Cannot find the gramer tag in template:" + key);
		}
		tagtKeys.removeAll(keySet);
		iterator = tagtKeys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			logger.warn("Cannot find the feild in java Map or Object:" + key);
		}

	}

	public static void debug(XWPFTemplate template, Object datas) {
		debug(template, convert2Map(datas));
	}

	/**
	 * 自我渲染
	 * 
	 * @param template
	 */
	public static void selfRender(XWPFTemplate template) {
		if (null == template)
			throw new POIXMLException("Template is null,should be setted first.");
		List<ElementTemplate> elementTemplates = template.getElementTemplates();
		if (null == elementTemplates || elementTemplates.isEmpty())
			return;
		RenderPolicy policy = null;
		for (ElementTemplate runTemplate : elementTemplates) {
			logger.debug("TagName:{}, Sign:{}", runTemplate.getTagName(), runTemplate.getSign());
			policy = template.getConfig().getDefaultPolicys().get(Character.valueOf('\0'));
			policy.render(runTemplate, new TextRenderData(runTemplate.getSource()), template);
		}
	}

	public static void render(XWPFTemplate template, Map<String, Object> datas) {
		if (null == template)
			throw new POIXMLException("Template is null, should be setted first.");
		List<ElementTemplate> elementTemplates = template.getElementTemplates();
		if (null == elementTemplates || elementTemplates.isEmpty() || null == datas || datas.isEmpty())
			return;
		Configure config = template.getConfig();
		RenderPolicy policy = null;

		int docxNum = 0;
		for (ElementTemplate runTemplate : elementTemplates) {
			logger.debug("TagName:{}, Sign:{}", runTemplate.getTagName(), runTemplate.getSign());
			policy = config.getPolicy(runTemplate.getTagName(), runTemplate.getSign());
			if (null == policy)
				throw new RenderException("cannot find render policy: [" + runTemplate.getTagName() + "]");
			if (policy instanceof DocxRenderPolicy) {
				docxNum++;
				continue;
			}
			policy.render(runTemplate, datas.get(runTemplate.getTagName()), template);
		}
		try {
			if (docxNum >= 1)
				template.reload(template.getXWPFDocument().generate());
			for (int i = 0; i < docxNum; i++) {
				elementTemplates = template.getElementTemplates();
				if (null == elementTemplates || elementTemplates.isEmpty() || null == datas || datas.isEmpty())
					break;
				for (ElementTemplate runTemplate : elementTemplates) {
					logger.debug("Docx TagName:{}, Sign:{}", runTemplate.getTagName(), runTemplate.getSign());
					policy = config.getPolicy(runTemplate.getTagName(), runTemplate.getSign());
					if (null != policy && policy instanceof DocxRenderPolicy) {
						policy.render(runTemplate, datas.get(runTemplate.getTagName()), template);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("render docx error", e);
		}

	}

	public static void render(XWPFTemplate template, Object dataSrouce) {
		render(template, convert2Map(dataSrouce));
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> convert2Map(Object dataSrouce) {
		if (dataSrouce instanceof Map)
			return (Map<String, Object>) dataSrouce;
		Map<String, Object> ret = new HashMap<String, Object>();
		Class<?> clazz = dataSrouce.getClass();
		while (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			PropertyDescriptor pd = null;
			for (Field f : fields) {
				try {
					pd = new PropertyDescriptor(f.getName(), dataSrouce.getClass());
					Name annotation = f.getAnnotation(Name.class);
					Object value = pd.getReadMethod().invoke(dataSrouce);
					ret.put(null == annotation ? f.getName() : annotation.value(), value);
				} catch (Exception e) {
					logger.error("Convert datasource field failed:{}", f.getName(), e);
				}
			}
			clazz = clazz.getSuperclass();
		}

		return ret;
	}

}
