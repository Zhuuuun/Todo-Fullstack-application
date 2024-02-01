function LargePanel({ children, className }) {
  return (
    <div
      className={
        "w-6/12 rounded-3xl p-6 border-2 border-black-100 shadow-xl " +
        className
      }
    >
      {children}
    </div>
  );
}

export default LargePanel;
