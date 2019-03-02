import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';
import { IProduct } from 'app/shared/model/product.model';

export interface IReview {
    id?: number;
    createdOn?: Moment;
    message?: any;
    rating?: number;
    customer?: ICustomer;
    product?: IProduct;
}

export class Review implements IReview {
    constructor(
        public id?: number,
        public createdOn?: Moment,
        public message?: any,
        public rating?: number,
        public customer?: ICustomer,
        public product?: IProduct
    ) {}
}
