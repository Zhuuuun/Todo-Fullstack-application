import { forwardRef } from "react";

import SmallText from "./SmallText";
import ErrorText from "./ErrorText";

const DropdownInput = forwardRef(function DropdownInput(
  { fieldName, options, error },
  ref
) {
  return (
    <>
      <div className="flex justify-between items-baseline">
        <SmallText>{fieldName}</SmallText>
        {error && <ErrorText>*{error}</ErrorText>}
      </div>
      <select className="w-full rounded-xl h-4/6 px-3 my-2" ref={ref}>
        {options.map((item) => (
          <option value={item}>{item}</option>
        ))}
      </select>
    </>
  );
});

export default DropdownInput;
