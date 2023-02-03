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
rewards
```

### GET --  get customer {id} rewards in {month}

**Request url:** localhost:8000/customers/{id}/rewards/{month}

**Respond Code:** 200 OK

**Respond:**
```json
rewards
```


### POST

**Request url:** localhost:8080/customers

**Request body:** raw json format

**Respond code:** 201 CREATED

**Respond:**
```json
id 
```

