package cn.xzc.utils;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.xzc.domain.Good;
import cn.xzc.domain.Category;
import cn.xzc.service.BusinessService;
import cn.xzc.service.Impl.BusinessServiceImpl;

//主要把请求数据封装到一个JavaBean中
public class WebUtils {
	
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Good upload(HttpServletRequest request, String uploadPath) {
		try {
			Good good = new Good();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding(request.getCharacterEncoding());
			
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String inputName = item.getFieldName();
					String value = item.getString("UTF-8");
					if ("category_id".equals(inputName)) {
						BusinessService service = new BusinessServiceImpl();
						Category c = service.findCategory(value);
						good.setCategory(c);
					} else {
						BeanUtils.setProperty(good, inputName, value);
					}
				} else {
					String filename = item.getName();//拿到上传文件的名称
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					File savepath = new File(uploadPath);
					if(!savepath.exists()) {
						savepath.mkdirs();
			        }
					String saveFilename = UUID.randomUUID().toString() + filename;
			        
			        File uploadFile=new File(savepath+File.separator+saveFilename);
			        item.write(uploadFile);
			  
					
					item.delete();
					good.setImage(saveFilename);
				}
			}
			good.setId(UUID.randomUUID().toString());
			return good;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}


