import { IDepartment } from 'app/shared/model/department.model';
import { IProduct } from 'app/shared/model/product.model';

export interface ICategory {
    id?: number;
    name?: string;
    description?: string;
    department?: IDepartment;
    products?: IProduct[];
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public department?: IDepartment,
        public products?: IProduct[]
    ) {}
}
