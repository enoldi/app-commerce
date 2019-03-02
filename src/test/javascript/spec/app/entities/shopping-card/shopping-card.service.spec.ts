/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ShoppingCardService } from 'app/entities/shopping-card/shopping-card.service';
import { IShoppingCard, ShoppingCard } from 'app/shared/model/shopping-card.model';

describe('Service Tests', () => {
    describe('ShoppingCard Service', () => {
        let injector: TestBed;
        let service: ShoppingCardService;
        let httpMock: HttpTestingController;
        let elemDefault: IShoppingCard;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ShoppingCardService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new ShoppingCard(0, 0, 'AAAAAAA', 0, false, currentDate, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        addedOn: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ShoppingCard', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        addedOn: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        addedOn: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new ShoppingCard(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ShoppingCard', async () => {
                const returnedFromService = Object.assign(
                    {
                        itemId: 1,
                        attributes: 'BBBBBB',
                        quantity: 1,
                        buyNow: true,
                        addedOn: currentDate.format(DATE_FORMAT),
                        salary: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        addedOn: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ShoppingCard', async () => {
                const returnedFromService = Object.assign(
                    {
                        itemId: 1,
                        attributes: 'BBBBBB',
                        quantity: 1,
                        buyNow: true,
                        addedOn: currentDate.format(DATE_FORMAT),
                        salary: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        addedOn: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ShoppingCard', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
