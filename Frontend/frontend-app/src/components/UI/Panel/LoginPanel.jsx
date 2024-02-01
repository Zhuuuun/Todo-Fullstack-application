import { useState } from "react";
import { useNavigate } from "react-router-dom";

import DefaultButton from "../../common/DefaultButton";
import Input from "../../common/Input";
import LargePanel from "../../common/LargePanel";
import TitleText from "../../common/TitleText";
import { login } from "../../../services/UserService";
import ErrorText from "../../common/ErrorText";

function LoginPanel() {

  const navigate = useNavigate() 

  const [data, setData] = useState({
    userName: "",
    password: "",
  });

  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { value, name } = e.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const user = await login(data)
      navigate("/")
    } catch(err) {
      setError(err.response.data);
    }
  }

  return (
    <div className="flex justify-center items-center h-screen w-screen">
      <LargePanel className="bg-blue-200 h-[30rem]">
        <div className="flex flex-col w-full h-full">
          <div className="mb-10">
            <TitleText>🔒 Login</TitleText>
          </div>
          <form
            onSubmit={handleLogin}
            className="flex flex-col w-full h-full justify-between"
          >
            <div className="h-2/6 space-y-12">
              <div className="h-1/2">
                <Input
                  type="text"
                  name="userName"
                  value={data.userName}
                  onChange={handleChange}
                  fieldName="Username"
                />
              </div>

              <div className="h-1/2">
                <Input
                  type="password"
                  name="password"
                  value={data.password}
                  onChange={handleChange}
                  fieldName="Password"
                />
              </div>
              {error && <ErrorText>*{error.errorMessage}</ErrorText>}
            </div>

            <div className="flex justify-end items-end">
              <div className="space-x-7">
                <DefaultButton type="submit">Login</DefaultButton>
                <DefaultButton onClick={() => navigate("/register")} >Register</DefaultButton>
              </div>
            </div>
          </form>
        </div>
      </LargePanel>
    </div>
  );
}

export default LoginPanel;
