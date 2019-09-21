package contracts.character

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name('should get Luke by id')
    request {
        method 'GET'
        url '/api/characters/1'
        headers {
            accept 'application/vnd.star-wars.v1+json;charset=UTF-8'
        }
    }
    response {
        status 200
        headers {
            contentType 'application/vnd.star-wars.v1+json;charset=UTF-8'
        }
        body([
                id: '1',
                name: 'Luke Skywalker',
                gender: 'male',
                birthYear: '19BBY',
                height: '172',
                weight: '77',
                skinColor: 'fair',
                eyeColor: 'blue',
                hairColor: 'blond',
                homeWorld: [
                        id: '1',
                        name: 'Tatooine'
                ],
                species: [
                        [
                                id: '1',
                                name: 'Human'
                        ]
                ]
        ])
    }
}
