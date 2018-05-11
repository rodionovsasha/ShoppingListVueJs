[![Build Status](https://travis-ci.org/rodionovsasha/ShoppingListVueJs.svg?branch=master)](https://travis-ci.org/rodionovsasha/ShoppingListVueJs)

# ShoppingList Vue.js

### Build the application
<pre>
mvn clean install
</pre>
or using wrapper
<pre>
./mvnw clean install
</pre>

### Run backend
<pre>
mvn --projects shoppinglist-backend spring-boot:run
</pre>
or simply run the application in your IDE using public static void main method.

Open the following URL in browser:
<pre>
http://localhost:8090/api/v1/
</pre>
Port and context path can be configured in `application.yml` properties.
`8090` is a default port and can be overridden in a command line as well:
```
mvn spring-boot:run -Dport=9090
```

### Swagger
http://localhost:8090/api/v1/swagger-ui.html

### Rest JSON API URL
```
http://localhost:8090/api/v1/
```

### Run frontend
``` bash
# go to frontend module
cd shoppinglist-frontend

# install dependencies
npm install

# serve with hot reload at http://localhost:8091
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run all tests
npm test
```
