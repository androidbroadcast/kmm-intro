//
//  MoviesVC.swift
//  iosApp
//
//  Created by Anna Zharkova on 18.12.2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit
import shared

class MoviesVC: UIViewController {
    private lazy var service: MoviesService = {
       return MoviesService()
    }()
    
    private var adapter = MoviesAdapter()
    
 

    @IBOutlet weak var listView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.listView.register(UINib(nibName: "MoviesCell", bundle: nil), forCellReuseIdentifier: "MoviesCell")
      
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.listView.delegate = adapter
        self.listView.dataSource = adapter
        self.service.loadMovies { [weak self] (response,error) in
            guard let self = self else {return}
            if let data = response?.content?.results {
                self.adapter.updateItems(items: data)
                self.listView.reloadData()
            }
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.listView.delegate = nil
        self.listView.dataSource = nil
        super.viewWillDisappear(animated)
    }

}
