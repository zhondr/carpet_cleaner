import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class MapComponent implements OnInit {

  public placemarkProperties = {
    hintContent: 'Hint content',
    balloonContent: 'Baloon content'
  }

  public placemarkOptions = {
    iconLayout: 'default#image',
    iconImageHref: 'https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678111-map-marker-512.png',
    iconImageSize: [32, 32]
  };

  constructor() { }

  ngOnInit() {}

}
