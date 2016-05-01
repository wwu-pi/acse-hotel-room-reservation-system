The following zip-archive contains 4 Eclipse projects. All of them can be imported by
choosing File -> Import -> General -> Existing Projects into Workspace and 
using Browse to find the archive on your file system.

Projects:

1) Hotel Java
- contains a naive Java implementation of a hotel room reservation system (HRRS)
based on Java SE, Java SWT, JDBC and MySQL
- there is also a corresponding DDL document for MySQL

2) Hotel Java DAO
- contains a modified HRRS, where the
data access has been moved to DAOs
- this approach is the blueprint for the automatically generated system
- the DDL for MySQL is attached

3) HotelJavaDAOgenerated
- in this version of the HRRS, the data layer has been generated completely
(that's why the layout is ugly)
- there is one protected region in class Room, which has been implemented manually
- the DDL has been polished manually

4) HotelUMLClassDiagramWithStereotypes
- is a Papyrus project with a UML class diagram of the HRRS
and with a corresponding UML profile
- the project name must not contain blanks (or special characters) 

Herbert Kuchen