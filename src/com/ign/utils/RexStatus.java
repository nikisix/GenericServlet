package com.ign.utils;

public class RexStatus
{
	public static enum StatusType
	{
		Success,
		Failure,
		RecommendationEngineFailure,
		DatabaseFailure,
		NoResults,
		InvalidRequestId,
		InvalidGameId,
		InvalidUserId,
		InvalidRatingId,
		InvalidRatingValue,
		
		Uninitialized
	};
		
	String Type = null;
	Integer Code = -1;
	String Msg = null;
	String Details = null;
	String Data = null;
	
	public void setType(StatusType type){
		Type = type.toString();
	}
	
	public RexStatus(String type, Integer code, String msg)
	{
		Type = type;
		Code = code;
		Msg = msg;
	}
	
	public void setDetails(String details)
	{
		Details = details;
	}
	
	public void setData(String data)
	{
		Data = data;
	}

	public void setMsg(String message){
		Msg = message;
	}

	public void setCode(int code){
		Code = code;
	}
	
	public Integer getCode()
	{
		return Code;
	}
	
	public String getMessage()
	{
		return Msg;
	}
	
	public String getDetails()
	{
		return Details;
	}
	
	public String getData()
	{
		return Data;
	}
}
