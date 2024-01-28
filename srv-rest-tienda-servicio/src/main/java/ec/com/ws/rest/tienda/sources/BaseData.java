package ec.com.ws.rest.tienda.sources;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import ec.com.ws.rest.tienda.util.ApplicationUtil;

public class BaseData implements Serializable{
	private static final long serialVersionUID = 1L;

	
	static HashMap<String, Object> entityProperties(LocalContainerEntityManagerFactoryBean em, Environment env) {
        HashMap<String, Object> properties = new HashMap<>();
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        properties.put("hibernate.jdbc.lob.non_contextual_creation",ApplicationUtil.getString("hibernate.jdbc.lob.non_contextual_creation"));
        properties.put("hibernate.generate_statistics", ApplicationUtil.getString("hibernate.generate_statistics"));
        properties.put("hibernate.use_sql_comments", ApplicationUtil.getString("hibernate.use_sql_comments"));
        properties.put("hibernate.format_sql", ApplicationUtil.getString("hibernate.format_sql"));
        properties.put("hibernate.ddl-auto", ApplicationUtil.getString("hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", ApplicationUtil.getString("hibernate.show_sql"));
        return properties;
    }
	
	

}
