import Input from "../../common/Input"


function RegisterInput({data, error,handleChange}) {
    const inputPairs = [
        [
          {
            type : "text",
            name : "firstName",
            value : data.firstName,
            onChange :handleChange,
            fieldName : "First Name"
          },
          {
            type : "text",
            name : "lastName",
            value : data.lastName,
            onChange :handleChange,
            fieldName : "Last Name"
          },
        ],
      
        [
          {
          type : "text",
          name : "userName",
          value : data.userName,
          onChange :handleChange,
          fieldName : "Username"
          },
          {
          type : "text",
          name : "phone",
          value : data.phone,
          onChange :handleChange,
          fieldName : "Phone Number"
          },
        ],
      
        [
          {
            type : "password",
            name : "password",
            value : data.password,
            onChange :handleChange,
            fieldName : "Password"
          },
          {
          type : "password",
          fieldName : "Confirm Password"
          },
        ],
          
    ]

    return (
        <div className="flex flex-col space-y-12 h-full">
            {inputPairs.map((inputPair) => (
                <div className="flex w-full h-1/3 space-x-10" key={inputPair[0].name}>
                    {inputPair.map((input) =>  (
                        <div key={input.name} className="w-1/2">
                            <Input 
                            {...input} 
                            error={error?.[input.name]}
                            />
                        </div>
                    ))}
                </div>
            ))}
        </div>     
    )

}

export default RegisterInput