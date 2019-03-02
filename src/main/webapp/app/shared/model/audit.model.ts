import { Moment } from 'moment';
import { IOrders } from 'app/shared/model/orders.model';

export interface IAudit {
    id?: number;
    createdOn?: Moment;
    message?: any;
    code?: number;
    order?: IOrders;
}

export class Audit implements IAudit {
    constructor(public id?: number, public createdOn?: Moment, public message?: any, public code?: number, public order?: IOrders) {}
}
