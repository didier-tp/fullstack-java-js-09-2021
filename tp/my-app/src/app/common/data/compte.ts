export class Compte {
    constructor(
        public numero : number | undefined | null = undefined,
        public label : string = "",
        public solde : number = 0
    ){}
}