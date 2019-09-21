package contracts.character

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name('should get characters')
    request {
        method 'GET'
        urlPath('/api/characters') {
            queryParameters {
                parameter 'page': $(p('0'), c(regex(number())))
                parameter 'size': $(p('20'), c(regex(number())))
            }
        }
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
                first: true,
                last: true,
                empty: false,
                number: fromRequest().query('page'),
                size: fromRequest().query('size'),
                numberOfElements: 2,
                totalElements: 2,
                totalPages: 1,
                content: [
                        [
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
                        ],
                        [
                                id: '5',
                                name: 'Leia Organa',
                                gender: 'female',
                                birthYear: '19BBY',
                                height: '150',
                                weight: '49',
                                skinColor: 'light',
                                eyeColor: 'brown',
                                hairColor: 'brown',
                                homeWorld: [
                                        id: '2',
                                        name: 'Alderaan'
                                ],
                                species: [
                                        [
                                                id: '1',
                                                name: 'Human'
                                        ]
                                ]
                        ]
                ]
        ])
    }
}
