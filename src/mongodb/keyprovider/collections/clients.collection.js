// Use DB:
use keyprovider

// Insert Client:
- JWt Service:
db.clients.insert( { "_id" : 1, 
"issuer" : "JWTSERVICEDEV", 
"serviceName" : "JWT_SERVICE-DEV", 
"serviceKeyAlias" : "jwtservice-dev", 
"servicePwd" : "ZGV2Snd0U2VydmljZTIwMTk=" } 
);

db.clients.insert( { "_id" : 2, 
"issuer" : "JWTSERVICEDEV-SEC", 
"serviceName" : "JWT_SERVICE-DEV-SEC", 
"serviceKeyAlias" : "jwtservice_secret-dev", 
"servicePwd" : "" }
)
