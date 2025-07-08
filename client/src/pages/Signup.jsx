    import {useState} from 'react'
    import {useForm} from "react-hook-form"
    import { Link ,useNavigate} from 'react-router-dom';
    import TextField from '../components/TextField';
    import api from '../api/api';
    import toast from 'react-hot-toast';

    const Signup = () => {
        const navigate = useNavigate();
        const [loader, setLoader] = useState(false);
        const {
            register,
            handleSubmit,
            reset,
            formState:{errors}
        }=useForm({
            defaultValues: {
                username: "",
                email: "",
                password: "",
            },
            mode: "onTouched",
        });
        const registerHandler=async (data)=>{
            setLoader(true);
            try {
                const { data: response } = await api.post(
                    "/api/auth/public/register",
                    data
                );
                reset();
                toast.success("Registration Successful!");
                setTimeout(() => navigate("/login"), 1000);
            } catch (error) {
                console.log(error);
                toast.error("Registeration Failed!")
            } finally {
                setLoader(false);
            }       
        }
    return (
        <div className="min-h-[calc(100vh-64px)] flex justify-center items-center">
            <form onSubmit={handleSubmit(registerHandler)} className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
                <h1 className="text-center font-serif text-[#3364F7] font-bold lg:text-3xl text-2xl">
                    Register Here
                </h1>

                <hr className='mt-2 mb-5 text-black'/>
            <div className='flex flex-col'>
                <TextField
                label="username"
                required
                id="username"
                type="text"
                message="Type your username"
                placeholder="Ex: Batman"
                register={register}
                errors={errors}
                />
                <TextField
                label="Email"
                required
                id="email"
                type="email"
                message="Enter your Email"
                placeholder="Ex:Batman@gmail.com"
                register={register}
                errors={errors}
                />
                <TextField
                label="Password"
                required
                id="password"
                type="password"
                message="Choose a strong Password"
                placeholder="Ex:password"
                register={register}
                errors={errors}
                />
                <button
                    disabled={loader}
                    type='submit'
                    className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                    {loader ? "Loading..." : "Register"}
                </button>
                <p className='text-center font-semibold text-sm text-slate-700 mt-6'>
                    Already have an Account?
                    <Link to={"/login"} className='font-semibold underline hover:text-black'><span className='text-[#3364F7]'>Signin</span></Link>
                </p>
            </div>
            

            </form>
        </div>
    )
    }

    export default Signup