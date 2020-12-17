package com.pipecode.topshop.configuration.util;

import utils.mapp.UBean;

public class UApp
{
	public static <T extends Object> T parseDtoToEnty(Object h, Class<T> dtoClass)
	{
		try
		{
			T dto = dtoClass.getConstructor().newInstance() ;
			String atts[] = UBean.getAtts(h);
			for(String a:atts)
			{
				Object v = UBean.invokeGetter(h, a);
				UBean.invokeSetter(dto, a, v);
			}
			
			return dto;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
        
        
        
        
        
        
        public static <T extends Object> T parseEntyToDto(Object h, Class<T> dtoClass)
	{
		try
		{
			T dto = dtoClass.getConstructor().newInstance() ;
			String atts[] = UBean.getAtts(dto);
			for(String a:atts)
			{
				Object v = UBean.invokeGetter(h, a);
				UBean.invokeSetter(dto, a, v);
			}
			
			return dto;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
        
        
        
}
