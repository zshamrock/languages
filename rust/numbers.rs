fn main() {
    let numbers = vec![1i, 2i, 3i];
    let numbers1 = vec![4i, 5i, 6i];
    let (tx, rx) = channel();
    tx.send(numbers);

    let (tx1, rx1) = channel();
    tx1.send(numbers1);

    spawn(proc() {
        let numbers = rx.recv();
        println!("{}", numbers[0]);
    });

    spawn(proc() {
        let numbers = rx1.recv();
        println!("{}", numbers[0]);
    });

    println!("End of program!");
}
