package de.ItsAMysterious.mods.reallifemod.core.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import net.minecraftforge.common.config.Configuration;

public class Config {
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface CfgId {
		public boolean block() default false;
	}
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface CfgBool {}


	public static void  load(Configuration config) {
		try {
			config.load();
			Field[] fields = Config.class.getFields();
			for(Field field : fields) {
				CfgId annotation = field.getAnnotation(CfgId.class);

			}
		} catch(Exception e) {
		
		} finally {
			config.save();
		}
	}

}
