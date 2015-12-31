# kegan

> We see evil cloaking our planet like a black cloud. [...] We see the Jedi
> surrounded by darkness. [...] The darkness comes from within them and then
> spreads to engulf them. Perhaps our destruction will come from an explosive
> device sent to destroy an entire planet without a shiver. [...] We see
> masked soldiers. We do not know who they are, or what they want. Only that
> they are evil. They will bring fear and suffering.
>
> -- O-Vieve and V-Tan, in *Jedi Apprentice: The Fight for Truth*

`kegan` monitors a changing structured value and tells you *how* it changes,
producing an immutable log of events, modeled as fact assertions and
retractions. This lets you tie mutable data to a system that expects immutable
data. That immutable data can go into a log manager, or a database like a la
[Datalog][datalog] like [Datomic][datomic] and [Datascript][datascript] that
lets you query not just the current state, but also the *evolution* of that
state efficiently. It supports arbitrarily nested and structured data
automatically by inspecting its structure, with no code to write.

For example, if you want to monitor a JSON REST API (push or pull) and log
changes to a log processor or dump them into [Datascript][datascript] to see
how it changed, `kegan` is for you.

`kegan` is named after [Kegan][kegan-star-wars], a planet in the Star Wars
universe where citizens' moves are meticulously watched and noted.

[datalog]: https://en.wikipedia.org/wiki/Datalog
[datomic]: http://www.datomic.com/
[datascript]: https://github.com/tonsky/datascript
[kegan-star-wars]: http://starwars.wikia.com/wiki/Kegan

## License

Copyright © 2015 Rackspace Hosting, Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
