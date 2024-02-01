function Footer() {
  return (
    <div className="flex flex-col justify-center items-center w-screen space-y-6 bg-emerald-100 mt-32 py-20">
      <div className="flex space-x-20">
        <a href="https://github.com/Zhuuuun/Portfolio" target="_blank">
          <img src="/images/icons/github-icon.svg" alt="Github Icon" className="h-6" />
        </a>
        <a href="mailto:kobkit.zhun@gmail.com?subject = Feedback&body = Message" target="_blank">
          <img src="/images/icons/email-icon.svg" alt="LinkedIn Icon" className="h-6" />
        </a>
        <a
          href="https://www.linkedin.com/in/kobkit-ruangsuriyakij-79ba5528b/"
          target="_blank"
        >
          <img src="/images/icons/linkedIn-icon.svg" alt="Github Icon" className="h-6" />
        </a>
      </div>

      <div className="">
        <p className="">© 2024 Copyright : Kobkit R. </p>
      </div>
    </div>
  );
}

export default Footer;
