import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IShoppingCard } from 'app/shared/model/shopping-card.model';

type EntityResponseType = HttpResponse<IShoppingCard>;
type EntityArrayResponseType = HttpResponse<IShoppingCard[]>;

@Injectable({ providedIn: 'root' })
export class ShoppingCardService {
    public resourceUrl = SERVER_API_URL + 'api/shopping-cards';

    constructor(protected http: HttpClient) {}

    create(shoppingCard: IShoppingCard): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(shoppingCard);
        return this.http
            .post<IShoppingCard>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(shoppingCard: IShoppingCard): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(shoppingCard);
        return this.http
            .put<IShoppingCard>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IShoppingCard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IShoppingCard[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(shoppingCard: IShoppingCard): IShoppingCard {
        const copy: IShoppingCard = Object.assign({}, shoppingCard, {
            addedOn: shoppingCard.addedOn != null && shoppingCard.addedOn.isValid() ? shoppingCard.addedOn.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.addedOn = res.body.addedOn != null ? moment(res.body.addedOn) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((shoppingCard: IShoppingCard) => {
                shoppingCard.addedOn = shoppingCard.addedOn != null ? moment(shoppingCard.addedOn) : null;
            });
        }
        return res;
    }
}
