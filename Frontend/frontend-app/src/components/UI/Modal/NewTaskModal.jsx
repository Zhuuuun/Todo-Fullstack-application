import ModalInput from "../Input/ModalInput";
import TitleText from "../../common/TitleText";

export default function NewTaskModal({ isOpen, onClose }) {
  return (
    <dialog open={isOpen}>
      <div className="fixed inset-0 bg-black bg-opacity-50 z-50 flex justify-center items-center">
        <div className="w-4/6 h-4/6 rounded-xl p-8 flex flex-col space-y-3 bg-blue-200">
          <div className="my-3">
            <TitleText>Adding new task ⚡</TitleText>
          </div>
          <ModalInput onClose={onClose} />
        </div>
      </div>
    </dialog>
  );
}
