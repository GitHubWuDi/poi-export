package com.vrv.vap.exportAndImport.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.vap.exportAndImport.word.config.Configure;
import com.vrv.vap.exportAndImport.word.exception.ResolverException;
import com.vrv.vap.exportAndImport.word.policy.RenderPolicy;
import com.vrv.vap.exportAndImport.word.render.RenderAPI;
import com.vrv.vap.exportAndImport.word.resolver.TemplateResolver;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;

/**
 * 模板
 * 
 * @author wudi
 * @version 0.0.1
 */
public class XWPFTemplate {
	private static Logger logger = LoggerFactory.getLogger(XWPFTemplate.class);
	private NiceXWPFDocument doc;
	private Configure config;

	private List<ElementTemplate> eleTemplates;

	private XWPFTemplate() {}

	/**
	 * @param filePath
	 * @return
	 */
	@Deprecated
	public static XWPFTemplate create(String filePath) {
		return compile(filePath);
	}

	/**
	 * @param file
	 * @return
	 */
	@Deprecated
	public static XWPFTemplate create(File file) {
		return compile(file);
	}

	/**
	 * @version 0.0.4
	 */
	public static XWPFTemplate compile(String filePath) {
		return compile(new File(filePath));
	}

	public static XWPFTemplate compile(File file) {
		return compile(file, Configure.createDefault());
	}
	
    /**
     * template file as InputStream
     * @param inputStream
     * @return
     * @version 1.2.0
     */
    public static XWPFTemplate compile(InputStream inputStream) {
        return compile(inputStream, Configure.createDefault());
    }

	/**
	 * @param filePath
	 * @param config
	 * @return
	 * @version 1.0.0
	 */
	public static XWPFTemplate compile(String filePath, Configure config) {
		return compile(new File(filePath), config);
	}
	

	/**
	 * @param file
	 * @param config
	 * @return
	 * @version 1.0.0
	 */
	public static XWPFTemplate compile(File file, Configure config) {
		try {
            return compile(new FileInputStream(file), config);
        } catch (FileNotFoundException e) {
            logger.error("Cannot find the file", e);
            throw new ResolverException("Cannot find the file [" + file.getPath() + "]");
        }
	}


	/**
	 * template file as InputStream
	 * @param inputStream
	 * @param config
	 * @return
	 * @version 1.2.0
	 */
	public static XWPFTemplate compile(InputStream inputStream, Configure config) {
		try {
			XWPFTemplate instance = new XWPFTemplate();
			instance.config = config;
			instance.doc = new NiceXWPFDocument(inputStream);
			instance.eleTemplates = new TemplateResolver(instance.config)
					.parseElementTemplates(instance.doc);
			return instance;
		} catch (IOException e) {
			logger.error("Compile template failed", e);
			throw new ResolverException("Compile template failed");
		}
	}
	
	/**
	 * 重新解析doc
	 * @param doc
	 */
	public void reload(NiceXWPFDocument doc) {
	    try {
            this.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.doc = doc;
        this.eleTemplates = new TemplateResolver(this.config).parseElementTemplates(doc);
    }

	public XWPFTemplate render(Map<String, Object> datas) {
		RenderAPI.render(this, datas);
		return this;
	}

	public XWPFTemplate render(Object datasource) {
		RenderAPI.render(this, datasource);
		return this;
	}

	/**
	 * @param templateClass
	 * @param policy
	 * @deprecated 1.0.0
	 */
	@Deprecated
	public void registerPolicy(Class<?> templateClass, RenderPolicy policy) {
		this.registerPolicy(templateClass.getName(), policy);
	}

	/**
	 * 自定义模板对应的策略
	 * 
	 * @param templateName
	 * @param policy
	 */
	@Deprecated
	public void registerPolicy(String templateName, RenderPolicy policy) {
		config.customPolicy(templateName, policy);
	}

	/**
	 * @param clazz
	 * @return
	 */
	@Deprecated
	public RenderPolicy getPolicy(Class<? extends ElementTemplate> clazz) {
		return config.getCustomPolicys().get(clazz.getName());
	}

	@Deprecated
	public RenderPolicy getPolicy(String templateName) {
		return config.getCustomPolicys().get(templateName);
	}

	public void write(OutputStream out) throws IOException {
		this.doc.write(out);
	}

	public void close() throws IOException {
		this.doc.close();
	}

	public List<ElementTemplate> getElementTemplates() {
		return eleTemplates;
	}

	public NiceXWPFDocument getXWPFDocument() {
		return this.doc;
	}

	public Configure getConfig() {
		return config;
	}

    

}
