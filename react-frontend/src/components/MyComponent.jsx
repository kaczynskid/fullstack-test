import React, {useEffect, useState} from "react"

/**
 * This component should show the users
 * @returns {*}
 * @constructor
 */
const MyComponent = () => {
    const [ip, setIp] = useState("0.0.0.0");
    useEffect(() => {
        //Call spring back-end
        setIp("0.0.0.0")
    }, []);

    return <div>
        {ip}
    </div>
};

export default MyComponent
