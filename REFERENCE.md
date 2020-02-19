## Document Reference

#### LOG
```
{
  "id": "5df55e5ca96a4b28d5da242a",
  "name": "sota-20191116",
  "userFields": {
    "summitName": "Aconcagua",
    "summitCode": "LUM/PH-001"
  }
}
```
The log document is used to identify groups of contacts.  For example, you might have separate logs for each contest entry.
- Fields:
    - [id] An automatically generated ID.
    - [name] * An identifier for the log.
    - [userFields] A string:string map of any additional fields.

#### Station
```
{
  "id": "5df55e5ca96a4b28d5da242a",
  "name": "Home - Main Radio",
  "operatorName": "Scott",
  "operatorCall": "K3GDS",
  "gridSquare": "FN00bl",
  "userFields": {
    "radio": "Yaesu 450D",
    "antenna": "1.8MHz L"
  }
}
```
The station document is used to identify unique operating locations, call signs, operators, or equipment.  For example, you would have different stations when operating at home and portable.
- Fields:
    - [id] An automatically generated ID.
    - [name] * An identifier for the station.
    - [operatorName] The name of the station operator.
    - [operatorCall] * The call sign used at the station.
    - [gridSquare] * The maidenhead grid location of the station.
    - [userFields] A string:string map of any additional fields.

#### Contact
```
{
  "id": "5df55e5ca96a4b28d5da242a",
  "dxCall": "9A1A",
  "dateTime": "1997-02-15 18:55",
  "frequency": "14.225",
  "mode": "CW",
  "stationId": "5df55e5ca96a4b28d5da242b",
  "logId": "5df55e5ca96a4b28d5da242c",
  "rstR": "599",
  "rstS": "599",
  "userFields": {
    "exchangeR": "KW",
    "exchangeS": "PA"
  }
}
```
The contact document records an individual QSO details.
- Fields:
    - [id] An automatically generated ID.
    - [dxCall] * The call sign of the DX station contacted.
    - [dateTime] * The UTC date and time of the contact in YYYY-MM-DD HH:MM format.
    - [frequency] * The MHz frequency of the contact.
    - [mode] * The mode of the contact.
    - [stationId] * The ID of the station document to associate with this contact.
    - [logId] * The ID of the log document to associate with this contact.
    - [rstR] The received signal report.
    - [rstS] The sent signal report.
    - [userFields] A string:string map of any additional fields.

_* - Required Field_

## API Reference

In the below examples, document type is one of `logs`, `stations`, or `contacts` (per the above reference). 

#### GET .../{document type}/

> **Returns** 200 OK & All documents of the specified type as a list of JSON objects.

#### GET .../{document type}/{id}

> **Path Param** [ID] The ID of the document to locate.

> **Returns** 200 OK & One document of the specified type that matches the path's ID.

> **404 Error** If no document is found with the specified ID.

#### POST .../{document type}

> **Request Body** The new document to save. 

> **Returns** 200 OK & The newly saved document (now with assigned ID).

> **400 Error** If the document in the request body includes an ID, if the operator call or grid square format is invalid when saving a station, or if the DX call or frequency format is invalid when saving a contact.

> **404 Error** If the station or log is not found with the specified ID when saving a contact.

#### PUT .../{document type}/{id}

> **Path Param** [ID] The ID of the document to update.

> **Request Body** The document to update.

> **Returns** 200 OK & The updated document.

> **400 Error** If the document in the request does not include an ID, if the path ID and document ID do not match, if the operator call or grid square format is invalid when saving a station, or if the DX call or frequency format is invalid when saving a contact.

> **404 Error** If the document is not found with the specified ID or if the station or log is not found with the specified ID when saving a contact.

#### DELETE .../{document type}/{id}

> **Path Param** [ID] The ID of the document to delete.

> **Returns** 200 OK if the document is deleted successfully. A call to delete a log or station will also delete contacts that reference the document’s ID.

> **404 Error** If the document is not found with the specified ID.
