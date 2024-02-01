function LeftNavbar() {
    return (
        <div className=' w-2/12 h-full rounded-l-full flex items-center space-x-5'>
            <div className='flex items-center justify-center w-2/6 h-full rounded-l-full'>
                <img src='/images/logos/app-logo.svg' className='w-12 h-12' />
            </div>
            <div className='w-4/6 h-full flex items-center font-logo text-4xl'>
                Todoes
            </div>
        </div>
    )
}

export default LeftNavbar