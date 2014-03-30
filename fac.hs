module Main where

fac :: (Integral n) => n -> n
fac 0 = 1
fac n = n * fac(n-1)

readInt :: IO Int
readInt = readLn

main :: IO ()
main = do x <- readInt
          putStrLn $ "Factorial of " ++ (show x) ++ " is " ++ (show (fac x))



