# Key Provider Service :books: #
_Microservice to provide key to validator jwt._

##Table of Contents##
*******

 1. [Starting](#starting)
 2. [Compile project](#compile-project)
 3. [Pre-requisites](#pre-requisites)
 4. [Building whit](#building-with)
 5. [Installation](#installation)
 6. [Testing](#testing)
 7. [License](#license)
 8. [Authors](#authors)

*******

### Starting ###
Clone project.
`
git clone https://github.com/golivaresh/mc-keyprovider-service.git
`

### Compile project :pick: ###

Compile with maven 3.6.1:

```
   1. Access the folder "/src/java-maven/mac-key provider-service" from the root folder of the project.
      Execute:
      > mvn clean package
```

### Pre-requisites ###

Things that are needed to install the software.

   * Tools

### Building with: :hammer_and_pick: ###
(Tools used for construction, example:)

* [Apache Maven 3.6.1](https://maven.apache.org/) - Dependency manager

### Installation :rocket: ###

    1. Step for install the project.

### Testing :memo: ###

For testing execute:

*Secret Key*

```
curl -vk -X GET -H "Content-Type: application/json" "http://localhost:8091/keyprovider/private/secretKey?issuer=JWTSERVICEDEV-SEC&service-name=JWT_SERVICE-DEV-SEC"
```

*Private Key*

```
curl -vk -X GET -H "Content-Type: application/json" "http://localhost:8091/keyprovider/private/privatekey?issuer=JWTSERVICEDEV&service-name=JWT_SERVICE-DEV"
```

*Public Key*

```
curl -vk -X GET -H "Content-Type: application/json" "http://localhost:8091/keyprovider/public/publickey?issuer=JWTSERVICEDEV&service-name=JWT_SERVICE-DEV"
```

### License :page_facing_up: ###
~~
This project is under license [GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.html).

See the file [LICENSE.md](LICENSE.md) for more details.
~~ 

### Authors :nerd_face: ###

* **Gustavo Olivares Hernández** - *Initial work* - [golivaresh](https://github.com/golivaresh)
