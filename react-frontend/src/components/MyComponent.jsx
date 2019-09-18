import React, {useEffect, useState} from "react"

/**
 * This component should show some information about a Star Wars character.
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [name, setName] = useState("Luke");
    useEffect(() => {
        //Call spring back-end
        setName("Luke")
    }, []);

    return <div>
        Name: {name}
    </div>
};

export default MyComponent
