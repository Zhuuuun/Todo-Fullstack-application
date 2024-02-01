function DefaultButton({ children, type, ...props }) {
  return (
    <button
      type={type}
      className="px-7 py-3 my-2 rounded-full 
                border-solid border-2 border-zinc-100 font-title 
                hover:bg-zinc-800 hover:text-white transition-all duration-300"
      {...props}
    >
      {children}
    </button>
  );
}

export default DefaultButton;
