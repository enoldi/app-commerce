export interface ITax {
    id?: number;
    taxType?: string;
    taxPercentage?: number;
}

export class Tax implements ITax {
    constructor(public id?: number, public taxType?: string, public taxPercentage?: number) {}
}
