# API design

## Customers API

### GET -- all customers

**Request url:** localhost:8000/customers

**Respond Code:** 200 OK

**Respond:**
```json
{"customerDTOList":[{"id":1,"userName":"Carol","email":"xxxx@gmail.com","rewards":0.0},{"id":2,"userName":"Nate","email":"xxxx@gmail.com","rewards":1.0}]]}
```

### GET --  get customer {id} rewards

**Request url:** localhost:8000/customers/{id}/rewards

**Respond Code:** 200 OK

**Respond:**
```json
90
```

### GET --  get customer {id} rewards in {month}

**Request url:** localhost:8000/customers/{id}/rewards/{month}

**Respond Code:** 200 OK

**Respond:**
```json
0
```


### POST

**Request url:** localhost:8080/customers

**Request body:** raw json format

**Respond code:** 201 CREATED

**Respond:**
```json
1 
```


### PUT

**Request url:** localhost:8080/customers/1

**Respond code:** 200 OK

**Respond:**
```json
1 
```



### DELETE

**Request url:** localhost:8080/customers/1

**Respond code:** 200 OK

**Respond:**
```json
1 
```



## Transcations API


### POST

**Request url:** localhost:8080/transcations

**Request body:** raw json format

**Respond code:** 201 CREATED

**Respond:**
```json
2
```

