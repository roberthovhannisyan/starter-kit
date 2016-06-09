## Task 1

Create a RESTful web service endpoint that allows a client to input a product name as a GET query parameter and returns the cheapest product.

The result should return the best (minimum) price for the product and which store to buy it from. If there are multiple products, always return the lowest priced product. For example:

**Request**
```
GET /product/search?name=ipad
```

**Example Response**
```
200 OK

{
    "productName": "iPad Mini",
    "bestPrice": "150.00",
    "currency": "CAD",
    "location": "Walmart"
}
```
