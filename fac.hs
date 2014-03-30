module Main where

import System.Environment

fac :: (Integral n) => n -> n
fac 0 = 1
fac n = n * fac(n-1)

readInt = do (x:[]) <- getArgs
             return (read x::Int)

main :: IO ()
main = do n <- readInt
          putStrLn $ "Factorial of " ++ (show n) ++ " is " ++ (show (fac n))



