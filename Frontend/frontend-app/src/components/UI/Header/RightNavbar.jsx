import { useNavigate } from "react-router-dom";
import DefaultButton from "../../common/DefaultButton";
import { getUserName, logout } from "../../../services/UserService";

function RightNavbar() {
  const navigate = useNavigate() 

  const handleLogout = () => {
    logout()
    navigate("/login")
  }


  return (
    <div className="w-2/12 h-full rounded-r-full flex items-center space-x-5">
      <div className="flex items-center w-1/2 h-full justify-center ">
        <a href="#" className="hover:underline">{getUserName()}</a>
      </div>
      <div className="w-1/2 h-full flex items-center justify-center font-primary text-sm">
        <DefaultButton onClick={handleLogout}>Logout</DefaultButton>
      </div>
    </div>
  );
}

export default RightNavbar;
