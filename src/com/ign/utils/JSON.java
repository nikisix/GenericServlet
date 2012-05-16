package com.ign.utils;

public class JSON
{
	public static String Build(Status status, String results)
	{
		String n = "\n"; String six = "      ";
	    String ret =
		"{\n" +
		"   \"response\":\n" +
		"   {\n" +
		"      \"status\":\n" +
		"      {\n" +
		"         \"code\": " + status.getCode() + ",\n" +
		"         \"message\": \"" + status.getMessage() + "\",\n" +
		"         \"details\": \"" + status.getDetails() + "\",\n" +
		"         \"data\": \"" + status.getData() + "\"\n" +
		"      }\n";
		
		if(results.length()>0){
			ret+=",\n      \"result\":\n";
			for(String str : results.split("\n")) 
				ret+=six+str+n;
		}

		ret = ret.concat("   }\n}");
		return ret;
	}
	
	public static void main(String ... args){
		String n = "\n";
		String ret = "" +
"{" +n+
"	outerField" +n+
"	{" +n+
"		innerField" +n+
"		[" +n+
"			{game, score}," +n+
"			{game, score}" +n+
"		]" +n+
"	}" +n+
"}" +n+
"";
		Status status = new Status(Status.StatusType.Success.toString(),0,"Success");
		System.out.println(JSON.Build(status, ret));
	}
}
