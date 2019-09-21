package contracts.character

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name('should get Leia by name')
    request {
        method 'GET'
        url '/api/characters?search=Leia'
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
                number: 0,
                size: 20,
                numberOfElements: 1,
                totalElements: 1,
                totalPages: 1,
                content: [
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
