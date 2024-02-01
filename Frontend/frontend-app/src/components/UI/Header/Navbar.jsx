import { randomQuotes } from "../../../utils/HeaderUtils";
import SmallText from "../../common/SmallText";
import LeftNavbar from "./LeftNavbar";
import RightNavbar from "./RightNavbar";

function Navbar() {
  const qoute = randomQuotes();
  return (
    <div className="flex justify-center h-20 my-5 px-10 shadow-xl rounded-full border-2 border-black-100 space-x-10">
      <LeftNavbar />
      <div className="flex justify-center items-center w-7/12 h-full">
        <SmallText>{qoute}</SmallText>
      </div>
      <RightNavbar />
    </div>
  );
}

export default Navbar;
