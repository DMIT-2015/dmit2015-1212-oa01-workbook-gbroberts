package common.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;

//@BasicAuthenticationMechanismDefinition(
//		realmName = "jaspitest"
//)

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage="/customLogin.xhtml",
				useForwardToLogin = false,
				errorPage=""
		)
)

@LdapIdentityStoreDefinition(
		url = "ldap://192.168.235.129:389",
		callerSearchBase = "ou=Departments,dc=dmit2015,dc=ca",
		callerNameAttribute = "SamAccountName", // SamAccountName or UserPrincipalName
		groupSearchBase = "ou=Departments,dc=dmit2015,dc=ca",
		bindDn = "cn=DAUSTIN,ou=IT,ou=Departments,dc=dmit2015,dc=ca",
		bindDnPassword = "Password2015",
		priority = 5
)
// lower priority will execute first, then higher priority will execute if lower not found
// Intranet uses LDAP... internet uses database identity
// dataSourceLookup needs to match the name of a DataSourceDefinition
// '?' is a parameter
// will compare password hashes
@DatabaseIdentityStoreDefinition(
		dataSourceLookup="java:app/datasources/mssqlDS",
		callerQuery="SELECT password FROM CallerUser WHERE username = ?",
		groupsQuery="SELECT groupname FROM CallerGroup WHERE username = ? ",
		priority = 10
)

@DataSourceDefinitions({

		@DataSourceDefinition(
				name="java:app/datasources/mssqlDS",
				className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
				url="jdbc:sqlserver://DMIT-Capstone1.ad.sast.ca;databaseName=DMIT2015_1212_A01_groberts14;TrustServerCertificate=true",   // change A01 to E01 if you are in section E01, change yourNaitUsername
				user="groberts14",  //  change yourNaitUsername
				password="RemotePassword200493791"),    // Replace 200012345 with your NAIT StudentID

})

@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}