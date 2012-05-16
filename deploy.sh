#this script goes in the root directory of the servlet project
#the same level as the WebContent directory
rm -r WebContent/WEB-INF/classes/
cp -r build/classes WebContent/WEB-INF/
cd WebContent
jar -cvf GenericServlet.war .
cp GenericServlet.war /usr/local/Cellar/tomcat/7.0.21/libexec/webapps/
#catalina run 
