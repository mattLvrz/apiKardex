package utils.mapp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class UBean
{
	public static Object invokeGetter(Object tgt, String attName)
	{
		try
		{
			String getterName = "get"+Character.toUpperCase(attName.charAt(0))+attName.substring(1);
			Method mtd = tgt.getClass().getMethod(getterName, null);
			return mtd.invoke(tgt, null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void invokeSetter(Object tgt, String attName, Object param)
	{
		try
		{
			Field f = tgt.getClass().getDeclaredField(attName);
			String setterName = "set"+Character.toUpperCase(attName.charAt(0))+attName.substring(1);
			Method mtd = tgt.getClass().getMethod(setterName, f.getType());
			mtd.invoke(tgt, param);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String[] getAtts(Object obj)
	{
		try
		{
			Field[] fields = obj.getClass().getDeclaredFields();

			String ret[] = new String[fields.length];
			int i=0;
			for(Field f:fields)
			{
				ret[i]=f.getName();
				i++;
			}
			
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}
