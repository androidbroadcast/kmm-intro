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
    private lazy var viewModel: MoviesListViewModel = {
        return MoviesListViewModel()
    }()
    
    private var adapter = MoviesAdapter()
    
    @IBOutlet weak var listView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.listView.register(UINib(nibName: "MoviesCell", bundle: nil), forCellReuseIdentifier: "MoviesCell")
      bindViewModel()
    }
    
    func bindViewModel() {
        self.viewModel.moviesList.bind {  [weak self](list) in
            guard let self = self else {return}
            if let items = list?.results {
                self.adapter.updateItems(items: items)
                self.listView.reloadData()
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.adapter.delegate = self
        self.listView.delegate = adapter
        self.listView.dataSource = adapter
        self.viewModel.loadMovies()
     
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.listView.delegate = nil
        self.listView.dataSource = nil
        self.adapter.delegate = nil
        super.viewWillDisappear(animated)
    }

}

extension MoviesVC : TableOwner {
    func select(index: Int) {
        guard  let item =  self.viewModel.getMovie(index: Int32(index)) else {
            return
        }
        let vc = MovieItemVC()
        vc.setup(item)
        self.navigationController?.pushViewController(vc, animated: true)
    }
}


