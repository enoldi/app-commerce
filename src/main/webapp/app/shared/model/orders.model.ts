import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';
import { IShipping } from 'app/shared/model/shipping.model';
import { ITax } from 'app/shared/model/tax.model';

export interface IOrders {
    id?: number;
    createdOn?: Moment;
    shippedOn?: Moment;
    status?: number;
    comments?: string;
    authCode?: string;
    reference?: string;
    customer?: ICustomer;
    shipping?: IShipping;
    tax?: ITax;
}

export class Orders implements IOrders {
    constructor(
        public id?: number,
        public createdOn?: Moment,
        public shippedOn?: Moment,
        public status?: number,
        public comments?: string,
        public authCode?: string,
        public reference?: string,
        public customer?: ICustomer,
        public shipping?: IShipping,
        public tax?: ITax
    ) {}
}
