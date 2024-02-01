import axios from "axios";

const API_URL = "http://localhost:8080/api"

export const fetchTasks = async (userName) => {
    try {
        const response = await axios.get(
            API_URL + `/tasks/${userName}`, 
            {
                headers : {
                    "Authorization" : `Bearer ${localStorage.getItem("token").slice(1,-1)}`
                }       
            }
        )
        return response.data 

    } catch (err) {
        throw err
    }
   
}

export const moveNext = async({taskId}) => {
    try {
        const response = await axios.put(
            API_URL + `/task/status/${taskId}`,null, 
            {
                headers : {
                    "Authorization" : `Bearer ${localStorage.getItem("token").slice(1,-1)}`
                }       
            }
        )
        return response.data

    } catch (err) {
        throw err
    }
}

export const createTask = async({body}) => {
    try{    
        const response = await axios.post(
            API_URL + "/task",
            body,
            {
                headers : {
                    "Authorization" : `Bearer ${localStorage.getItem("token").slice(1,-1)}`
                }       
            }    
        )

        return response.data

    } catch(err) {
        throw err
    }

}

export const removeTask = async({taskId}) => {

    try {
        const response = await axios.delete(
            API_URL + `/task/${taskId}`,
            {
                headers : {
                    "Authorization" : `Bearer ${localStorage.getItem("token").slice(1,-1)}`
                }       
            },
            null
        )
        return response.data

    } catch (err) {
        throw err
    }

}