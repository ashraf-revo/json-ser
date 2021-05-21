run the test cases 

the generated json 
```json
{
  "org.revo.jsonser.JsonPage": {
    "content": {
      "java.util.ArrayList": [
        {
          "org.revo.jsonser.domain.User": {
            "id": 1,
            "name": "ashraf",
            "status": "ACTIVE",
            "phones": {
              "org.hibernate.collection.internal.PersistentBag": [
                {
                  "org.revo.jsonser.domain.Phone": {
                    "id": 2,
                    "name": "p1"
                  }
                },
                {
                  "org.revo.jsonser.domain.Phone": {
                    "id": 3,
                    "name": "p2"
                  }
                }
              ]
            },
            "emails": {
              "org.hibernate.collection.internal.PersistentSet": [
                {
                  "org.revo.jsonser.domain.Email": {
                    "id": 4,
                    "name": "e1"
                  }
                },
                {
                  "org.revo.jsonser.domain.Email": {
                    "id": 5,
                    "name": "e2"
                  }
                }
              ]
            }
          }
        }
      ]
    },
    "pageable": {
      "org.springframework.data.domain.Unpaged": "INSTANCE"
    },
    "total": 1,
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "sort": {
      "org.springframework.data.domain.Sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      }
    },
    "number": 0,
    "numberOfElements": 1,
    "size": 1,
    "empty": false
  }
}
```
you could see that it contains `org.hibernate.collection.internal.PersistentSet` and `org.hibernate.collection.internal.PersistentBag`

if i switch to lazy on properties `User.phones` and `User.emails` it will convert the values to `Set` and `List`
