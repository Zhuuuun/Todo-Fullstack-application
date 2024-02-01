import { forwardRef } from "react";

import SmallText from "./SmallText";
import ErrorText from "./ErrorText";

const Input = forwardRef(function Input({ fieldName, error, ...props }, ref) {
  return (
    <div className="w-full h-full">
      <div className="flex justify-between items-baseline">
        <SmallText>{fieldName}</SmallText>
        {error && <ErrorText>*{error}</ErrorText>}
      </div>
      <input
        className={`w-full rounded-xl h-4/6 px-3 my-2 ${
          error && "border-2 border-rose-400"
        }`}
        ref={ref}
        {...props}
      />
    </div>
  );
});

export default Input;
