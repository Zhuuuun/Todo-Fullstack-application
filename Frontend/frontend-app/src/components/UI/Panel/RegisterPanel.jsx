import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../../../services/UserService";

import DefaultButton from "../../common/DefaultButton";
import LargePanel from "../../common/LargePanel";
import TitleText from "../../common/TitleText";
import RegisterInput from "../Input/RegisterInput";
import ErrorText from "../../common/ErrorText";

function RegisterPanel() {
  const navigate = useNavigate();

  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    phone: "",
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

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const res = await register(data);
      navigate("/login");
    } catch (err) {
      setError(err.response.data);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen w-screen">
      <LargePanel className="bg-red-200 h-[30rem]">
        <div className="flex flex-col w-full h-full">
          <div className="mb-5">
            <TitleText>📝 Register</TitleText>
          </div>

          <form
            onSubmit={handleRegister}
            className="flex flex-col w-full h-full justify-between"
          >
            <div className="h-2/5">
              <RegisterInput
                data={data}
                error={error}
                handleChange={handleChange}
              />
            </div>

            <div className="flex justify-between items-end">
              <div className="flex justify-center h-full items-center">
                {error && <ErrorText>*{error?.errorMessage}</ErrorText>}
              </div>
              <div className="space-x-7">
                <DefaultButton type="submit">Save</DefaultButton>
                <DefaultButton onClick={() => navigate("/login")}>
                  Back to login
                </DefaultButton>
              </div>
            </div>
          </form>
        </div>
      </LargePanel>
    </div>
  );
}

export default RegisterPanel;
