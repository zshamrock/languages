fn main() {
    let program = "+ + * * - /";
    let mut accumulator = 0i;

    for token in program.chars() {
        match token {
            '+' => accumulator += 1,
            '-' => accumulator -= 1,
            '*' => accumulator *= 2,
            '/' => accumulator /= 2,
            _ => { /* ignore */ }
        }
    }

    println!("The program \"{}\" calculates the value {}",
        program, accumulator);
}
