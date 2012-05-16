package com.ign.utils;

public class Status
{
	public static enum StatusType
	{
		Success,
		Failure,
		DatabaseFailure,
		NoResults,
        InvalidParameter,
		Uninitialized
	};
		
	String Type = null;
	Integer Code = -1;
	String Msg = null;
	String Details = null;
	String Data = null;

    public Status (){}

    public Status(String type, Integer code, String msg)
    {
        Type = type;
        Code = code;
        Msg = msg;
    }

    public void setCode(int code)           {	Code = code;	}
    public void setData(String data)    	{	Data = data;	}
    public void setDetails(String details)	{	Details = details;  }
    public void setMsg(String message)      {	Msg = message;	}
    public void setType(StatusType type)    {   Type = type.toString();	}
	public Integer getCode()            	{	return Code;	}
    public String getData()             	{	return Data;	}
    public String getDetails()          	{	return Details;	}
	public String getMessage()              {   return Msg;     }
    public String getType()                 {   return Type;    }
}
